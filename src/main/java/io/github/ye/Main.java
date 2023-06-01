package io.github.ye;

import com.google.common.reflect.ClassPath;
import io.github.ye.controllers.Controller;
import io.github.ye.injections.Inject;
import io.github.ye.injections.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("main");
        for (ClassPath.ClassInfo classInfo : ClassPath.from(Main.class.getClassLoader()).getAllClasses()) {
            if (!classInfo.getPackageName().startsWith(Main.class.getPackageName())) continue;
            Class<?> loadedClass = classInfo.load();
            Controller controller = loadedClass.getAnnotation(Controller.class);
            Service service = loadedClass.getAnnotation(Service.class);
            if (controller != null) {
                logger.info(String.format("Controller %s is for api prefix: %s", loadedClass.getName(), controller.value()));
            } else if (service != null) {
                logger.info(String.format("Class %s is for a service", loadedClass.getName()));
            }

            Field[] requiredFields = Arrays
                    .stream(loadedClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Inject.class))
                    .toArray(Field[]::new);


        }
    }
}
