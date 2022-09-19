package com.order.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeStampEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7402472388869946277L;

	/***
	 * This is used to store the unix timestamp when the record was inserted
	 */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private LocalDateTime createdDate;

	/***
	 * This is used to store the unix timestamp when the record was updated
	 */
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private LocalDateTime updatedDate;

	/***
	 * This is for versioning of the entity. This is important for optimistic
	 */
	@Version
	@Column(name = "version")
	private Long version;

}
