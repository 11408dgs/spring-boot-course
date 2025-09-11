package top.mqxu.boot.config.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class TeamTest {
    @Resource
    private Team team;

    @Test
    void testTeam1(){
        log.info("team:{}",team);
        assertEquals("sf",team.getLeader());
    }

    @Test
    void testTeam2(){
        assertEquals("19505050795",team.getPhone());
    }
}