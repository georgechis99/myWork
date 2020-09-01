import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        java.util.Map<String, String> languages = new HashMap<>();

        languages.put("Java", "a compiled, high level, object-oriented, platform independent language");
        languages.put("Python", "an interpreted, object-oriented, high level programming language with dynamin semantics");
        languages.put("Algol", "an algorithmic language");
        System.out.println(languages.put("BASIC", "Begginers All Purpose Symbolic Instruction Code"));
        System.out.println(languages.put("Lisp", "Therein lies madness"));

        if (languages.containsKey("Java")) {
            System.out.println("Java is already in the map");
        } else {
            languages.put("Java", "this course is about Java");
        }

        System.out.println("==============================================================");

//        languages.remove("Lisp");
//        if(languages.remove("Algol","an algorithmic language")){
//            System.out.println("Algol removed");
//        }else{
//            System.out.println("Algol not removed, key/values pair not found");
//        }

        if(languages.replace("Lisp","Therein lies madness","a functional programming languae with imperative features")){
            System.out.println("Lisp replaced");
        }else{
            System.out.println("Lisp was not replaced");
        }
//        System.out.println(languages.replace("Scala","this will not be added"));

        for (String key : languages.keySet()) {
            System.out.println(key + " : " + languages.get(key));
        }
    }
}
