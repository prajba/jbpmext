package org.jbpmext.servlet.cache;

import javax.script.*;
import java.io.*;
import java.util.*;
/*
 * 
 * CachedScript 类 接受一个脚本文件并只有当源代码有修改时才会进行重编译。
 * getCompiledScript() 方法会调用此脚本引擎的 compile()，
 * 进而返回 javax.script.CompiledScript 对象，该对象的 eval() 方法会执行脚本。 
 * 
 * 
 */
public class CachedScript {
    private Compilable scriptEngine;
    private File scriptFile;
    private CompiledScript compiledScript;
    private Date compiledDate;

    public CachedScript(Compilable scriptEngine, File scriptFile) {
        this.scriptEngine = scriptEngine;
        this.scriptFile = scriptFile;
    }
    
    public CompiledScript getCompiledScript()
            throws ScriptException, IOException {
        Date scriptDate = new Date(scriptFile.lastModified());
        if (compiledDate == null || scriptDate.after(compiledDate)) {
            Reader reader = new FileReader(scriptFile);
            try {
                compiledScript = scriptEngine.compile(reader);
                compiledDate = scriptDate;
            } finally {
                reader.close();
            }
        }
        return compiledScript;
        
    }
}
