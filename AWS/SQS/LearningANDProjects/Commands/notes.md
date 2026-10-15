## Commands for the SQS(General Usage) 
-  This will list all the attributes that SQS takes to create.
```
aws sqs get-queue-attributes help
```
-  This will help to create SQS by taking attributes in the json file.
```
aws sqs create-queue --queue-name 'que' --attributes file://create-queue-attributes.json
```

- Get the general information for the aws configured account in the console.
```
aws sts get-caller-identity
```

- This will help to send message to SQS make sure to have correct permissions from your AWS console.
```
aws sqs send-message --cli-input-json file://send-queue-message-attributes.json
```

-  This will help us to provide the messages to the terminal.
```
aws sqs receive-message --visibility-timeout 3600 --message-attribute-names attrib1 attrib2  --attribute-names All --queue-url <YOUR-QUEUE-URL>
```
