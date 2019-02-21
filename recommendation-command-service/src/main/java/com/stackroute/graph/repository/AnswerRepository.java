package com.stackroute.graph.repository;

import com.stackroute.graph.model.Answer;
import com.stackroute.graph.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Collection;

public interface AnswerRepository extends Neo4jRepository<Answer, Integer> {


    Long deleteById(int answerId);

    @Query("MATCH (m:Answer) <-[ACCEPTED]-(u:User) RETURN m,u")
    Collection<Answer> getAllAnswers();


    @Query("CREATE (a:User)-[r:ANSWERED]->(b:Answer) RETURN type(r)")
    Collection<Answer> getAllAnswered();


    @Query("match (q:User),(t:Answer) where q.userId={userid} and t.answerId={answerid} create (q)-[r:ANSWERED]->(t)")
    User createRelatioshipBetweenUserAndAnswer(int userId, int answerId);
}


