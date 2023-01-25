//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services;

import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Student;
import com.driver.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository3;

    public CardService() {
    }

    public Card createAndReturn(Student student) {
        Card card = new Card();
        card.setStudent(student);
        student.setCard(card);
        this.cardRepository3.save(card);
        return card;
    }

    public void deactivateCard(int student_id) {
        this.cardRepository3.deactivateCard(student_id, CardStatus.DEACTIVATED.toString());
    }
}
