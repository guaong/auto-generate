package guaong.permission.permission.bean;

import lombok.Data;

import java.util.List;

@Data
public class PermissionTree {

    private int id;

    // 和id一样
    private int key;

    private String path;

    private String title;

    private int value;

    private String component;

    private int pid;

    private int sort;

    private String icon;

    private int type;

    private List<PermissionTree> children;

    public PermissionTree(){}

    public PermissionTree(int id, int key, int value, String title){
        this.id = id;
        this.key = key;
        this.value = value;
        this.title = title;
    }

}
