package com.example.taskmanagement.dto;

import com.example.taskmanagement.entity.TaskStatus;

public class CreateTaskRequest {
    private String name;
    private String description;
    private String dueDate; //En String, se convertirá a LocalDateTime
    private TaskStatus status;
    private Long parentTaskId;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the status
	 */
	public TaskStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	/**
	 * @return the parentTaskId
	 */
	public Long getParentTaskId() {
		return parentTaskId;
	}
	/**
	 * @param parentTaskId the parentTaskId to set
	 */
	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

}
