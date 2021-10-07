package com.peopleplace.cafe.utils;

import com.peopleplace.cafe.enums.Role;
import com.peopleplace.cafe.model.User;

public class Helper {
    public static boolean notNull(Object obj){
        return obj != null;
    }
    /**
     * @param role
     * @return
     */
    public static boolean canCrudUser(Role role) {
        return role.equals(Role.admin) || role.equals(Role.manager);
    }

    /**
     * @param userUpdating
     * @param userIdBeingUpdated
     * @return
     */
    public static boolean canCrudUser(User userUpdating, Long userIdBeingUpdated) {
        Role role = userUpdating.getRole();
        // admin and manager can crud any user
        if (role.equals(Role.admin) || role.equals(Role.manager)) {
            return true;
        }
        // user can update his own record, but not his role
        return role.equals(Role.user) && userUpdating.getId().equals(userIdBeingUpdated);
    }
}
