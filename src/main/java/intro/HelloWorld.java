package intro;

import java.util.Optional;

public class HelloWorld {
    private String greeting ="Hello, %s from Mockito!";

    private final PersonRepository personRepository;
    private final TranslationService translatorService;

    public HelloWorld(PersonRepository personRepository, TranslationService translatorService) {
        this.personRepository = personRepository;
        this.translatorService = translatorService;
    }

    public String greet(int id,String sourceLang,String targetLang){
        Optional<Person> person = personRepository.findById(id);
        String name =person.map(Person::getName).orElse("World");
        return translatorService.translate(
                String.format(greeting,name),sourceLang,targetLang);

    }

}
