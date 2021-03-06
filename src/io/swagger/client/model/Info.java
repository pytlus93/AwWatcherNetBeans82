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

/**
 * Info
 */
public class Info {
  @SerializedName("hostname")
  private String hostname = null;

  @SerializedName("version")
  private String version = null;

  @SerializedName("testing")
  private Boolean testing = null;

  @SerializedName("device_id")
  private String deviceId = null;

  public Info hostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

   /**
   * Get hostname
   * @return hostname
  **/
  @ApiModelProperty(value = "")
  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public Info version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(value = "")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Info testing(Boolean testing) {
    this.testing = testing;
    return this;
  }

   /**
   * Get testing
   * @return testing
  **/
  @ApiModelProperty(value = "")
  public Boolean isTesting() {
    return testing;
  }

  public void setTesting(Boolean testing) {
    this.testing = testing;
  }

  public Info deviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

   /**
   * Get deviceId
   * @return deviceId
  **/
  @ApiModelProperty(value = "")
  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Info info = (Info) o;
    return Objects.equals(this.hostname, info.hostname) &&
        Objects.equals(this.version, info.version) &&
        Objects.equals(this.testing, info.testing) &&
        Objects.equals(this.deviceId, info.deviceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hostname, version, testing, deviceId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Info {\n");
    
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    testing: ").append(toIndentedString(testing)).append("\n");
    sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
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

