# Example of simple producer/consumer kafka application
This application has 3 applications that need to be run independently: kafka, consumer and producer

## Kafka
This app will run a kafka instance until it is manually terminated.

Ensure you have Docker running and then run the kafka instance with: 
```./run-kafka```

## Consumer
The consumer is configured to consume the messages from the topic starting with the earliest messages available 
and wait for more messages until the app is terminated manually.

Run with: 
```./run-consumer [<topic> <consumerGroup>]```

## Producer
The producer will produce 10 messages and then exit. 
Run with: 
```./run-producer [<topic>]```

## Things to try
What would happen in each of the following scenarios?
1. all running, then consumer goes down, producer produces messages, then consumer goes up
2. all running, then producer goes down, then producer goes up
3. all running, then kafka goes down, then the producer produces messages, then kafka goes up