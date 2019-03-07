package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.DatabaseConfig;
import com.lhc.datamodel.entities.competition.Ballot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DatabaseConfig.class)
public class BallotRepositoryTest {

    @Autowired
    BallotRepository ballotRepository;

    @Test
    public void findByReference() {

        Ballot ballot = ballotRepository.findByReference("e9414d6b-337a-4dff-ba38-d588a202cd91");

        assertThat(ballot).isNotNull();

    }
}