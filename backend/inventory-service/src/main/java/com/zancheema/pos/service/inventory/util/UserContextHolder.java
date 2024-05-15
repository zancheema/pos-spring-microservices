package com.zancheema.pos.service.inventory.util;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> context = new ThreadLocal<>();

    public static UserContext getContext() {
        if (context.get() == null) {
            context.set(new UserContext());
        }
        return context.get();
    }
}
