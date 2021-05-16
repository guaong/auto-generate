package guaong.quick.core.general.write;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.exception.FileException;
import guaong.quick.core.util.StringUtil;
import guaong.quick.core.exception.WriteFailException;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class GeneralWriter {

    public final static int CONTROLLER = 1;
    public final static int SERVICE_IMPL = 2;
    public final static int I_SERVICE = 3;
    public final static int ENTITY = 4;
    public final static int MAPPER = 5;
    public final static int MAPPER_XML = 6;

    private Map<Integer, String> generalMap;

    private TableConfigInfo tableConfigInfo;

    public GeneralWriter(Map<Integer, String> generalMap, TableConfigInfo tableConfigInfo){
        this.generalMap = generalMap;
        this.tableConfigInfo = tableConfigInfo;
    }

    public boolean write(){
        if (!createFile(tableConfigInfo.getExportUrl())) return false;
        Set<Integer> set = this.generalMap.keySet();
        String tableName = tableConfigInfo.getTableName();
        set.forEach(i -> {
            String url = tableConfigInfo.getExportUrl() + chooseFileName(i, tableName);
            if (!doWrite(url, generalMap.get(i)))
                throw new WriteFailException("File create fail");
        });
        return false;
    }

    // 可优化为树形结构
    private boolean createFile(String url){
        File file = new File(url);
        if (!file.exists()){
            if (!file.mkdirs()) return false;
            try {
                File f = new File(url + "/controller");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/controller fail");
                f = new File(url + "/service");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/service fail");
                f = new File(url + "/service/impl");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/service/impl fail");
                f = new File(url + "/entity");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/entity fail");
                f = new File(url + "/mapper");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/mapper fail");
                f = new File(url + "/mapper/xml");
                if (!f.mkdirs())
                    throw new FilerException("create " + url + "/mapper/xml fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean doWrite(String url, String content){
        File file = new File(url);
        try {
            if (!file.exists()){
                if (!file.createNewFile()) throw new FileException( url + " create fail");
            }
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String chooseFileName(int type, String name){
        String Name = StringUtil.convert2JClassName(name);
        if (type == CONTROLLER){
            return "/controller/" + Name + "Controller.java";
        }else if (type == SERVICE_IMPL){
            return "/service/impl/" + Name + "ServiceImpl.java";
        }else if (type == I_SERVICE){
            return "/service/" + "I" + Name + "Service.java";
        }else if (type == ENTITY){
            return "/entity/" + Name + ".java";
        }else if (type == MAPPER){
            return "/mapper/" + Name + "Mapper.java";
        }else if (type == MAPPER_XML){
            return "/mapper/xml/" + Name + "Mapper.xml";
        }
        return Name;
    }

}
