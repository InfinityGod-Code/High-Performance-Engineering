output "aws_lambda_role_arn" {
  value       = aws_iam_role.lambda_execution_role.arn
  description = "lambda ARN for the Lambda function created"
}

output "aws_lambda_role_name" {
  value       = aws_iam_role.lambda_execution_role.name
  description = "Name of the Lambda execution role"
}

output "aws_lambda_role_id" {
  value       = aws_iam_role.lambda_execution_role.id
  description = "ID of the Lambda execution role"
}

output "aws_lambda_function_arn" {
  value       = aws_lambda_function.application.arn
  description = "ARN of the Lambda function"
}

output "aws_lambda_invoke_arn" {
  value       = aws_lambda_function.application.invoke_arn
  description = "Invoke ARN of the Lambda function"
}