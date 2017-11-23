package by.tc.parsing.controller;

import by.tc.parsing.entity.Command;

import java.util.Map;

public class ViewContext {
    private Map<String,Command> map;
    private boolean more;
    private boolean less;
    private int onPage;
    private int startEntryIndex;
    private int endEntryIndex;

    public int getStartEntryIndex() {
        return startEntryIndex;
    }

    public void setStartEntryIndex(int startEntryIndex) {
        this.startEntryIndex = startEntryIndex;
    }

    public int getEndEntryIndex() {
        return endEntryIndex;
    }

    public void setEndEntryIndex(int endEntryIndex) {
        this.endEntryIndex = endEntryIndex;
    }

    public Map<String, Command> getMap() {
        return map;
    }

    public void setMap(Map<String, Command> map) {
        this.map = map;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public boolean isLess() {
        return less;
    }

    public void setLess(boolean less) {
        this.less = less;
    }

    public int getOnPage() {
        return onPage;
    }

    public void setOnPage(int onPage) {
        this.onPage = onPage;
    }

    @Override
    public String toString() {
        return "ViewContext{" +
                "map=" + map +
                ", more=" + more +
                ", less=" + less +
                ", onPage=" + onPage +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewContext)) return false;

        ViewContext that = (ViewContext) o;

        if (more != that.more) return false;
        if (less != that.less) return false;
        if (onPage != that.onPage) return false;
        return map != null ? map.equals(that.map) : that.map == null;
    }

    @Override
    public int hashCode() {
        int result = map != null ? map.hashCode() : 0;
        result = 31 * result + (more ? 1 : 0);
        result = 31 * result + (less ? 1 : 0);
        result = 31 * result + onPage;
        return result;
    }
}
