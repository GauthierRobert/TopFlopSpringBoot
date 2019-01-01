import com.lhc.business.BusinessConfig;
import com.lhc.business.service.security.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.sasl.AuthenticationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class SecurityTests {

    @Autowired
    private UserService userService;


    @Test
    public void authenticate() throws AuthenticationException {

        boolean actual = userService.authenticate("AAAA", "AAAA");
        assertThat(actual).isEqualTo(true);
    }
}
