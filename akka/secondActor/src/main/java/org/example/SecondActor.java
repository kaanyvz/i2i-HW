package org.example;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SecondActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("hello from actor 1", s -> {
                    System.out.println("Actor 2 received: " + s);
                    getSender().tell("hello from actor 2", getSelf());
                })
                .build();
    }
}

class SecondActorMain{
    public static void main(String[] args) {
        Config config = ConfigFactory.load("secondActor.conf");
        ActorSystem actorSystem = ActorSystem.create("Actor2System", config);
        actorSystem.actorOf(Props.create(SecondActor.class), "actor2");
    }
}