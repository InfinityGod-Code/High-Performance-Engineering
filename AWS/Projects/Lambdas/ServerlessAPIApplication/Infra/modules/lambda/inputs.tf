variable "lambda_file_path" {
  description = "Path to the Lambda function deployment package zip file."
  type        = string
  validation {
    condition     = length(trimspace(var.lambda_file_path)) > 0
    error_message = "The lambda_file_path variable must not be empty."
  }
}

variable "lambda_function_name" {
  type        = string
  description = "Name of the Lambda function."
  validation {
    condition     = length(trimspace(var.lambda_function_name)) > 0
    error_message = "The lambda_function_name variable must not be empty."
  }
}

variable "name_prefix" {
  description = "Optional prefix used for resource naming. Defaults to the lambda function name when empty."
  type        = string
  default     = ""
  validation {
    condition     = length(trimspace(var.name_prefix)) == 0 || length(trimspace(var.name_prefix)) > 0
    error_message = "The name_prefix variable must either be empty or contain a valid prefix."
  }
}

variable "tags" {
  description = "Tags applied to Lambda resources."
  type        = map(string)
  default     = {}
}

variable "memory_size" {
  description = "Memory size for the Lambda function in MB."
  type        = number
  default     = 512
  validation {
    condition     = var.memory_size > 0
    error_message = "The memory_size variable must be greater than 0."
  }
}

variable "timeout_seconds" {
  type        = number
  description = "Timeout for the Lambda function in seconds."
  default     = 30
  validation {
    condition     = var.timeout_seconds > 0
    error_message = "The timeout_seconds variable must be greater than 0."
  }
}

variable "arch_types" {
  description = "List of architectures for the Lambda function. Supported values: x86_64, arm64."
  type        = list(string)
  default     = ["x86_64"]
  validation {
    condition = alltrue([
      for arch in var.arch_types : contains(["x86_64", "arm64"], arch)
    ])
    error_message = "Each entry in arch_types must be either x86_64 or arm64."
  }
}

variable "lambda_handler" {
  type        = string
  description = "Handler for the Lambda function."
  validation {
    condition     = length(trimspace(var.lambda_handler)) > 0
    error_message = "The lambda_handler variable must not be empty."
  }
}

variable "lambda_runtime" {
  type        = string
  description = "Runtime for the Lambda function."
  default     = "java17"
  validation {
    condition     = length(trimspace(var.lambda_runtime)) > 0
    error_message = "The lambda_runtime variable must not be empty."
  }
}
