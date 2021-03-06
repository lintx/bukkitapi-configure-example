package org.lintx.example.Configure;

import org.bukkit.plugin.java.JavaPlugin;
import org.lintx.example.Configure.config.*;
import org.lintx.plugins.modules.configure.Configure;

import java.util.Map;

public class Example extends JavaPlugin {
    @Override
    public void onEnable() {
        Default config = new  Default();
        config.load(this);
        log("string1",config.string1);
        log("aliasstring",config.aliasstring);
        log("int1",""+config.int1);
        log("aliasint",""+config.aliasint);
        log("double1",""+config.double1);
        log("enum1",""+config.enum1);
        for (int i=0;i<config.liststr1.size();i++){
            log("liststr1["+(i+1)+"]",config.liststr1.get(i));
        }
        for (int i=0;i<config.listint1.size();i++){
            log("listint1["+(i+1)+"]",""+config.listint1.get(i));
        }
        log("child1.str1",config.child.str1);
        log("child1.int1",""+config.child.int1);
        log("child2.str1",config.child2.str1);
        log("child2.int1",""+config.child2.int1);
        log("child2.grandson.double1",""+config.child2.grandson.double1);
        for (Map.Entry<String,String> entry: config.map.entrySet()){
            log("map."+entry.getKey(),entry.getValue());
        }


        org.lintx.example.Configure.config.Example ex = new org.lintx.example.Configure.config.Example();
        ex.load(this);
        log("example.yml,string1",ex.string1);
        log("example.yml,string2",ex.string2);

        config.int1 += 1;
        config.child2.grandson.double1 += 1.0;
        log("new,int1",""+config.int1);
        log("new,child2.grandson.double1",""+config.child2.grandson.double1);
        config.save();

        Language zh_cn = new Language();
        zh_cn.load(this,"language_zh_cn.yml");
        log("zh_cn.string1",zh_cn.string1);

        Language en_us = new Language();
        en_us.load(this,"language_en_us.yml");
        log("en_us.string1",en_us.string1);

        DefaultLanguage not_file = new DefaultLanguage();
        not_file.load(this,"not_file.yml");
        log("not_file.string1",not_file.string1);

        NoYmlToSaveFile nofile = new NoYmlToSaveFile();
        nofile.load(this);
        nofile.save();

        NoInherit n = new NoInherit();
        Configure.bukkitLoad(this,n);
        log("NoInherit.example",n.example);
        Configure.bukkitSave(this,n);
        //这里有问题，没有保存，上面的save也没有保存
    }

    private void log(String name,String val){
        getLogger().info("name:"+name+",value:"+val);
    }
}
