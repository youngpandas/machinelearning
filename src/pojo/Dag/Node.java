package pojo.Dag;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @program: MachineLearning
 * @description: DAG的节点
 * @author: Mr.Sun
 * @create: 2019-05-05 13:50
 **/

public class Node implements Serializable {
    private int id;
    private ArrayList<Integer> in_ports;
    private String name;
    private ArrayList<Integer> out_ports;
    private int pos_x;
    private int pos_y;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Integer> getIn_ports() {
        return in_ports;
    }

    public void setIn_ports(ArrayList<Integer> in_ports) {
        this.in_ports = in_ports;
    }

    public ArrayList<Integer> getOut_ports() {
        return out_ports;
    }

    public void setOut_ports(ArrayList<Integer> out_ports) {
        this.out_ports = out_ports;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", in_ports=" + in_ports +
                ", name='" + name + '\'' +
                ", out_ports=" + out_ports +
                ", pos_x=" + pos_x +
                ", pos_y=" + pos_y +
                ", type='" + type + '\'' +
                '}';
    }
}
