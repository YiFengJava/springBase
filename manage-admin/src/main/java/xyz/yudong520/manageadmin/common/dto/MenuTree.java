package xyz.yudong520.manageadmin.common.dto;

import lombok.Data;
import java.util.Objects;
import java.util.Set;

@Data
public class MenuTree {
    private String code;
    private String pcode;
    private String name;
    private String value;
    private String ionc;
    private Set<MenuTree> childMenu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuTree menuTree = (MenuTree) o;
        return Objects.equals(code, menuTree.code) &&
                Objects.equals(pcode, menuTree.pcode) &&
                Objects.equals(name, menuTree.name) &&
                Objects.equals(value, menuTree.value) &&
                Objects.equals(ionc, menuTree.ionc) &&
                Objects.equals(childMenu, menuTree.childMenu);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, pcode, name, value, ionc, childMenu);
    }
}
