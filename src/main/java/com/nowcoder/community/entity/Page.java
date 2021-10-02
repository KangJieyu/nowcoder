package com.nowcoder.community.entity;

/**
 * 封装分页相关信息
 *
 * @author KangJieyu
 * @date 2021/9/21 20:29
 */
public class Page {

    /** 当前页码 */
    private int current = 1;

    /** 该页面显示数据的上限 */
    private int limit = 10;

    /** 查询的数据的总数，用于计算总页数 */
    private int rows;

    /** 查询路径，复用分页的链接 */
    private String path;

    /**
     * 获取当前页的起始行
     * @return
     */
    public int getOffset() {
        return current * limit - limit;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotal() {
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getFrom() {
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    /**
     * 获取终止页码
     * @return
     */
    public int getTo() {
        int to = current + 2;
        return to > getTotal() ? getTotal() : to;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
