package test.java;

import com.lhc.business.BusinessConfig;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.entities.VoteRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class MapperTests {

    @Autowired
    private MapperHandler mapperHandler;

    @Test
    public void VoteMapping(){

        com.lhc.business.dto.VoteDto vote = new com.lhc.business.dto.VoteDto();
        vote.setName("azerazeraz");
        vote.setPoints(4);
        vote.setReference("BE21412142");

        VoteRecord voteRecord = null;
        voteRecord = mapperHandler.mapVoteRecord(vote, voteRecord);

        Assert.assertEquals(vote.getReference(), voteRecord.getReference());

        VoteRecord voteRecord1 = new VoteRecord();
        voteRecord1.setId(new Long("545454"));
        voteRecord1.setPoints(0);
        voteRecord1 = mapperHandler.mapVoteRecord(vote, voteRecord1);

        Assert.assertEquals(vote.getReference(), voteRecord1.getReference());
        Assert.assertEquals(vote.getPoints(), voteRecord1.getPoints());
        Assert.assertEquals(new Long("545454"), voteRecord1.getId());

    }

}
