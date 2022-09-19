package com.order.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class OrderEntity extends TimeStampEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362076200437751655L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_info_id")
	private Long orderInfoId;

	private String orderNumber;

	@Lob
	private String customerInfo;

	private String clientId;

	@Lob
	private String orderItems;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime errorTimeStamp;

}
