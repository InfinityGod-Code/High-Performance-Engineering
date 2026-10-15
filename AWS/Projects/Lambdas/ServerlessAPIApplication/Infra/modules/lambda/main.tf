# IAM role for Lambda execution
locals {
  lambda_execution_role_name = coalesce(trimspace(var.name_prefix), var.lambda_function_name)
  lambda_description         = "Lambda function for the application"
}

data "aws_iam_policy_document" "assume_role" {
  statement {
    effect = "Allow"

    principals {
      type        = "Service"
      identifiers = ["lambda.amazonaws.com"]
    }

    actions = ["sts:AssumeRole"]
  }
}

resource "aws_iam_role" "lambda_execution_role" {
  name               = "${var.name_prefix}-lambda-role"
  assume_role_policy = data.aws_iam_policy_document.assume_role.json

  tags = merge(var.tags, {
    Name      = "${local.lambda_execution_role_name}-lambda-role"
    ManagedBy = "Terraform"
  })
}

resource "aws_iam_role_policy_attachment" "lambda_basic_execution" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_lambda_function" "application" {
  function_name    = var.lambda_function_name
  description      = "${local.lambda_description} ${var.lambda_function_name}"
  role             = aws_iam_role.lambda_execution_role.arn
  handler          = var.lambda_handler
  runtime          = var.lambda_runtime
  filename         = var.lambda_file_path
  source_code_hash = filebase64sha256(var.lambda_file_path)
  memory_size      = var.memory_size
  timeout          = var.timeout_seconds
  architectures    = var.arch_types
  
  depends_on = [aws_iam_role_policy_attachment.lambda_basic_execution]
}
