package org.jbpmext.servlet.cache;

import javax.script.*;

import org.jbpmext.servlet.cache.CachedScript;

import java.io.*;
import java.util.*;
/*
 * 
 * ScriptCache 类 使用 java.util.LinkedHashMap 对象为所编译的脚本实现存储库。map 的初始容量被设为所缓存脚本的最大数量并且加载系数是 1。这两个参数就确保了该 cacheMap 不需要重新处理。 

默认地，LinkedHashMap 类会使用条目的插入顺序。若不想使用默认顺序，LinkedHashMap() 构造函数的第三个参数必须是 true 以便使用条目的访问顺序。 

达到缓存的最大容量后，removeEldestEntry() 方法就会开始返回 true，以便当每次向此缓存添加一个新的编译了的脚本时，一个条目都会自动从 cacheMap 删除。 

通过联合使用 LinkedHashMap 的自动删除机制和访问顺序，ScriptCache 就确保了当添加了新脚本时，最近最少使用的（Least Recently Used，LRU）的脚本将能够从缓存中删除。 


 * 
 */
public abstract class ScriptCache {
    public static final String ENGINE_NAME = "JavaScript";
    private Compilable scriptEngine;
    private LinkedHashMap<String, CachedScript> cacheMap;

    public ScriptCache(final int maxCachedScripts) {
        ScriptEngineManager manager = new ScriptEngineManager();
        scriptEngine = (Compilable) manager.getEngineByName(ENGINE_NAME);
        cacheMap = new LinkedHashMap<String, CachedScript>(
                maxCachedScripts, 1, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxCachedScripts;
            }
        };
    }

    public abstract File getScriptFile(String key);
/*
 * getScript() 从缓存检索所编译的脚本
 * 
 */
    public synchronized CompiledScript getScript(String key)
            throws ScriptException, IOException {
        CachedScript script = cacheMap.get(key);
        if (script == null) {
            script = new CachedScript(scriptEngine, getScriptFile(key));
            cacheMap.put(key, script);
        }
        return script.getCompiledScript();
    }
    
    public ScriptEngine getEngine() {
        return (ScriptEngine) scriptEngine;
    }
    
}
