package intro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloWorldTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private TranslationService translationService;
    @InjectMocks
    private HelloWorld mock;



    ;
@Test
@DisplayName("Greet Nickan Kahn")
void greetPersonThatExist(){
    //when
    when(personRepository.findById(anyInt()))
            .thenReturn(Optional.of(new Person(1,"Nickan","Khan", LocalDate.of(2023, Month.DECEMBER,12))));

    when(translationService.translate("Hello, Nickan from Mockito!","en","fa"))
            .thenReturn("Hello, Nickan,from Mockito!");

    String greeting = mock.greet(1,"en","fa");
    assertEquals("Hello, Nickan,from Mockito!",greeting);

    InOrder inOrder = inOrder(personRepository,translationService);
    inOrder.verify(personRepository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(),eq("en"),eq("fa"));
}
@Test
void greetPersonThatNotExist(){
    when(personRepository.findById(anyInt()))
            .thenReturn(Optional.empty());
    when(translationService.translate("Hello, World from Mockito!","en","fa"))
            .thenReturn("Hello, World from Mockito!");

    String greeting = mock.greet(100,"en","fa");
    assertEquals("Hello, World from Mockito!",greeting);
    InOrder inOrder = inOrder(personRepository,translationService);
    inOrder.verify(personRepository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(),eq("en"),eq("fa"));

}

}