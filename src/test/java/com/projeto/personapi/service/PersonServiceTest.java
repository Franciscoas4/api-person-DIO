package com.projeto.personapi.service;

import com.projeto.personapi.dto.request.PersonDTO;
import com.projeto.personapi.dto.response.MessageResponseDTO;
import com.projeto.personapi.entity.Person;
import com.projeto.personapi.repository.PersonRepository;
import com.projeto.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.projeto.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSucessSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());

        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        assertEquals(expectedSavedPerson, succesMessage);
    }

    private MessageResponseDTO createExpectedSuccessMessage(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
