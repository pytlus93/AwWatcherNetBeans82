/*
 * API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;

/**
 * The Event model that is used in ActivityWatch
 */
@ApiModel(description = "The Event model that is used in ActivityWatch")
public class Event {
  @SerializedName("timestamp")
  private OffsetDateTime timestamp = null;

  @SerializedName("duration")
  private BigDecimal duration = null;

  @SerializedName("data")
  private Object data = null;

  public Event timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(required = true, value = "")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Event duration(BigDecimal duration) {
    this.duration = duration;
    return this;
  }

   /**
   * Get duration
   * @return duration
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getDuration() {
    return duration;
  }

  public void setDuration(BigDecimal duration) {
    this.duration = duration;
  }

  public Event data(Object data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.timestamp, event.timestamp) &&
        Objects.equals(this.duration, event.duration) &&
        Objects.equals(this.data, event.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, duration, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

