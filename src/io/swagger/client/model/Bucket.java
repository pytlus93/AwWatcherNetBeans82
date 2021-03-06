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
import io.swagger.client.model.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * The Bucket model that is used in ActivityWatch
 */
@ApiModel(description = "The Bucket model that is used in ActivityWatch")
public class Bucket {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("client")
  private String client = null;

  @SerializedName("hostname")
  private String hostname = null;

  @SerializedName("created")
  private OffsetDateTime created = null;

  @SerializedName("data")
  private Object data = null;

  @SerializedName("events")
  private List<Event> events = null;

  public Bucket id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The unique id for the bucket
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The unique id for the bucket")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Bucket name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The readable and renameable name for the bucket
   * @return name
  **/
  @ApiModelProperty(value = "The readable and renameable name for the bucket")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Bucket type(String type) {
    this.type = type;
    return this;
  }

   /**
   * The event type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The event type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Bucket client(String client) {
    this.client = client;
    return this;
  }

   /**
   * The name of the client that is reporting to the bucket
   * @return client
  **/
  @ApiModelProperty(required = true, value = "The name of the client that is reporting to the bucket")
  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Bucket hostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

   /**
   * The hostname of the machine on which the client is running
   * @return hostname
  **/
  @ApiModelProperty(required = true, value = "The hostname of the machine on which the client is running")
  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public Bucket created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

   /**
   * The creation datetime of the bucket
   * @return created
  **/
  @ApiModelProperty(value = "The creation datetime of the bucket")
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public Bucket data(Object data) {
    this.data = data;
    return this;
  }

   /**
   * 
   * @return data
  **/
  @ApiModelProperty(value = "")
  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Bucket events(List<Event> events) {
    this.events = events;
    return this;
  }

  public Bucket addEventsItem(Event eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<Event>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/
  @ApiModelProperty(value = "")
  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bucket bucket = (Bucket) o;
    return Objects.equals(this.id, bucket.id) &&
        Objects.equals(this.name, bucket.name) &&
        Objects.equals(this.type, bucket.type) &&
        Objects.equals(this.client, bucket.client) &&
        Objects.equals(this.hostname, bucket.hostname) &&
        Objects.equals(this.created, bucket.created) &&
        Objects.equals(this.data, bucket.data) &&
        Objects.equals(this.events, bucket.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, client, hostname, created, data, events);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Bucket {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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

