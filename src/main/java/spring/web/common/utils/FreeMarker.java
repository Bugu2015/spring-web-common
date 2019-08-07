package spring.web.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

// VM Options : -Dorg.freemarker.loggerLibrary=none
@Slf4j
public class FreeMarker {

    private static final Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
    private static final String name = "name";

    public static synchronized String renderByContent(String content, Map<String, Object> args) {
        StringWriter stringWriter = new StringWriter();
        try {
            Template template = new Template(name, new StringReader(content), configuration);
            template.process(args, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized String renderByFileName(String filename, Map<String, Object> args) {
        StringWriter stringWriter = new StringWriter();
        String filePath = filename.substring(0, filename.lastIndexOf(File.separator));
        String fileName = filename.substring(filename.lastIndexOf(File.separator)+1);
        render(stringWriter, filePath, fileName, args);
        return stringWriter.toString();
    }

    public static synchronized String renderByPath(String filePath, String filename, Map<String, Object> args) {
        StringWriter stringWriter = new StringWriter();
        render(stringWriter, filePath, filename, args);
        return stringWriter.toString();
    }

    private static void render(StringWriter writer, String path, String filename, Map<String, Object> args){
        try {
            configuration.setDirectoryForTemplateLoading(new File(path));
            configuration.getTemplate(filename).process(args, writer);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
