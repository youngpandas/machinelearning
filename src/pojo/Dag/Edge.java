package pojo.Dag;

/**
 * @program: MachineLearning
 * @description: DAG的边
 * @author: Mr.Sun
 * @create: 2019-05-05 13:48
 **/

public class Edge {
    private int dst_input_idx;
    private int dst_node_id;
    private int id;
    private int src_node_id;
    private int src_output_idx;

    public int getDst_input_idx() {
        return dst_input_idx;
    }

    public void setDst_input_idx(int dst_input_idx) {
        this.dst_input_idx = dst_input_idx;
    }

    public int getDst_node_id() {
        return dst_node_id;
    }

    public void setDst_node_id(int dst_node_id) {
        this.dst_node_id = dst_node_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrc_node_id() {
        return src_node_id;
    }

    public void setSrc_node_id(int src_node_id) {
        this.src_node_id = src_node_id;
    }

    public int getSrc_output_idx() {
        return src_output_idx;
    }

    public void setSrc_output_idx(int src_output_idx) {
        this.src_output_idx = src_output_idx;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "dst_input_idx=" + dst_input_idx +
                ", dst_node_id=" + dst_node_id +
                ", id=" + id +
                ", src_node_id=" + src_node_id +
                ", src_output_idx=" + src_output_idx +
                '}';
    }
}
