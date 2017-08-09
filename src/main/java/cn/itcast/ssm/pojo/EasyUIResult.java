package cn.itcast.ssm.pojo;

import java.util.List;

/**
 * Created by zm on 2017/8/7.
 */
public class EasyUIResult {
    private Long total;
    private List<?> rows;
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
