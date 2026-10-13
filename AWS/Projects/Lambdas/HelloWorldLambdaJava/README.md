# HelloWorldLambdaJava

Creates and deploys a "Hello World" AWS Lambda function, implemented in Java.

## Prerequisites

* Created: An AWS account, and an S3 bucket for storing temporary deployment artifacts (referred to as $CF_BUCKET below)
* Installed: Java 21, Maven, AWS CLI, SAM CLI
* Configured: AWS credentials in your terminal

## Usage

To build:

```
$ sam build
```

To deploy:

```
$ sam deploy --s3-bucket $CF_BUCKET --stack-name HelloWorldLambdaJava --capabilities CAPABILITY_IAM
```

To test:

The above will create a new function in Lambda, so you can test via the Lambda web console,
or via the CLI using `aws lambda invoke`.

## More Information

Please see https://github.com/symphoniacloud/sam-init-HelloWorldLambdaJava for more information.
