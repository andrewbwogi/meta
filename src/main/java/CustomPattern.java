import spoon.Launcher;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;
import java.util.List;

public class CustomPattern {

    void replaceInt(){

        // from getSpoonModelOf(String pathToSources, String pathToDependencies) in DSportCompiler
        Launcher launcher = new Launcher();
        launcher.addInputResource("/home/andrew/IdeaProjects/asmmaven/src/main/java/Test.java");
        launcher.buildModel();
        Factory factory = launcher.getFactory();
        List<CtType<?>> classes = factory.Type().getAll();
        List<CtElement> elements = classes.get(0).getMethodsByName("main").get(0).getElements(new TypeFilter<>(CtElement.class));
        System.out.println(elements.get(0));
        for (CtElement v : elements) {
            // todo: only set ints
            if (v instanceof CtLiteral) {

                // the transformation
                ((CtLiteral) v).setValue(5);
            }
        }
        System.out.println(elements.get(0));
    }
}
