package az.maqa.project.dao.inter;

import az.maqa.project.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoleList() throws Exception;
}
