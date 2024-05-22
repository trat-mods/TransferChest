package net.transferchest.mod.api;

import net.transferchest.mod.loader.TCLoader;

public final class ModLogger {
    public final static ModLogger DEFAULT_CHANNEL = new ModLogger(TCLoader.MOD_ID);

    private String domain;

    public ModLogger(String domain) {
        this.domain = domain;
    }

    public void logError(String message) {
        System.out.println("[" + domain + "]:(ERROR) => " + message);
    }

    public void logInfo(String message) {
        System.out.println("[" + domain + "]:(INFO) => " + message);
    }

    public void logWarning(String message) {
        System.out.println("[" + domain + "]:(WARNING) => " + message);
    }
}
