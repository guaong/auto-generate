package guaong.quick.core.create.creator;

import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.create.bean.FieldBean;
import guaong.quick.core.create.bean.MethodBean;
import guaong.quick.core.exception.InitException;
import guaong.quick.core.exception.TypeErrorException;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.create.translate.translator.JavaTranslator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * java creator
 *
 * @author guaong
 * @version 1.0
 * @see ClassCreator 生成class的creator
 * @see InterfaceCreator 生成interface的creator
 */
public abstract class JavaCreator implements ICreator, JavaTranslator {

    public final static int TYPE_CLASS = 1;
    public final static int TYPE_INTERFACE = 2;
    public final static int TYPE_ENUM = 3;
    public final static int TYPE_ANNOTATION = 4;

    /**
     * 用于存放生成的代码
     */
    private StringBuilder code;

    private ClassBean classBean;

    private TableDefinition tableDefinition;

    public JavaCreator(TableDefinition tableDefinition) {
        if (tableDefinition == null)
            throw new NullPointerException("tableDefinition is null");
        this.tableDefinition = tableDefinition;
    }

    @Override
    public StringBuilder create() {
        this.classBean = this.translate(this.tableDefinition);
        if (this.classBean == null)
            throw new NullPointerException("TableDefinition translate into ClassBean is null");
        code = new StringBuilder();
        this.addPackage();
        this.addImport(getImports());
        final StringBuilder classStr = this.addClass(classBean);
        code.append(classStr == null ? "" : classStr);
        this.start();
        final StringBuilder fieldStr = this.addField(classBean.getFieldList());
        code.append(fieldStr == null ? "" : fieldStr);
        final StringBuilder methodStr = this.addMethods(classBean);
        code.append(methodStr == null ? "" : methodStr);
        this.end();
        return code;
    }

    private void start() {
        checkInit();
        code.append(" {\n\n");
    }

    private void addPackage() {
        code.append("package ");
        code.append(this.classBean.getPackageUrl());
        code.append(";\n\n");
    }

    private void addImport(Set<String> imports) {
        checkInit();
        for (String str : imports) {
            code.append("import ");
            code.append(str);
            code.append(";\n");
        }
        code.append("\n");
    }

    private StringBuilder addClass(ClassBean classBean) {
        StringBuilder str = new StringBuilder();
        List<String> annotations = classBean.getAnnotationList();
        if (annotations != null) {
            for (String s : annotations) {
                str.append(s).append(this.newlines(1));
            }
        }

        str.append("public ").append(getClassType(classBean.getClassType())).append(" ");
        if (classBean.getClassName() != null) {
            str.append(classBean.getClassName());
        }
        if (classBean.getExtend() != null) {
            str.append(" extends ").append(classBean.getExtend());
        }
        if (classBean.getImplementList() != null) {
            str.append(" implements ");
            List<String> list = classBean.getImplementList();
            for (int i = 1; i <= list.size(); i++) {
                str.append(list.get(i - 1));
                if (i == list.size()) break;
                str.append(", ");
            }
        }
        return str;
    }

    public abstract StringBuilder addField(List<FieldBean> fields);

    public abstract StringBuilder addMethods(ClassBean classBean);

    private void end() {
        checkInit();
        code.append("}");
    }

    /**
     * 子类建议使用super.translate(TableDefinition)获取ClassBean
     * 如果不使用最好要在子类translate(TableDefinition)中setDefaultName
     * ClassBean#defaultName只会在本类的translate方法中赋值
     */
    @Override
    public ClassBean translate(TableDefinition tableDefinition) {
        ClassBean classBean = new ClassBean();
        classBean.setDefaultName(tableDefinition.getTableName());
        return classBean;
    }

    @Override
    public StringBuilder indentation(int num) {
        StringBuilder str = new StringBuilder();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                str.append("\t");
            }
        }
        return str;
    }

    @Override
    public StringBuilder newlines(int num) {
        StringBuilder str = new StringBuilder();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                str.append("\n");
            }
        }
        return str;
    }

    public String clipPackageUrl(String url) {
        if (url == null) throw new NullPointerException("url is null");
        String[] arr = url.split("\\.");
        return arr.length >= 1 ? arr[arr.length - 1] : "";
    }

    public String getClassType(int type) {
        if (type == TYPE_CLASS) return "class";
        else if (type == TYPE_INTERFACE) return "interface";
        else if (type == TYPE_ENUM) return "enum";
        else if (type == TYPE_ANNOTATION) return "@interface";
        else throw new TypeErrorException("Not java class type");
    }

    private void checkInit() {
        if (code == null) throw new InitException("Please use ICreator.create() before user this");
    }

    private Set<String> getImports() {
        Set<String> imports = new HashSet<>(classBean.getImportSet());
        List<FieldBean> fields = classBean.getFieldList();
        if (fields != null) {
            for (FieldBean field : fields
            ) {
                imports.addAll(field.getImportSet());
            }
        }
        List<MethodBean> methods = classBean.getMethodList();
        if (methods != null) {
            for (MethodBean method : methods
            ) {
                imports.addAll(method.getImportSet());
            }
        }
        return imports;
    }

}
