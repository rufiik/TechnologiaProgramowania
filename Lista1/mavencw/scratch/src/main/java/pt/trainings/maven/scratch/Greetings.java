package pt.trainings.maven.scratch;
public class Greetings {
    private static final String GREETING = "Hello ";
    public String sayHello(String string) {
    return GREETING + (string != null ? string : "Jonh Doe");
    }
    }
