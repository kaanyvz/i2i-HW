package org.example;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class FirstActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", s -> {
                    getContext().actorSelection("akka://Actor2System@localhost:25521/user/actor2")
                            .tell("hello from actor 1", getSelf());
                })
                .matchEquals("hello from actor 2", s -> {
                    System.out.println("actor 1 received: " + s);
                })
                .build();
    }
}

class FirstActorMain{
    public static void main(String[] args) {

        Config config = ConfigFactory.load("firstActor.conf");
        ActorSystem actorSystem = ActorSystem.create("Actor1System", config);
        actorSystem.actorOf(Props.create(FirstActor.class), "actor1");
        actorSystem.actorSelection("/user/actor1").tell("start", ActorSystem.create().deadLetters());

    }
}