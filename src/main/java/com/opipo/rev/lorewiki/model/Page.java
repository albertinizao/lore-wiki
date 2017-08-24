package com.opipo.rev.lorewiki.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Document
@ApiModel(value = "Page", description = "Page with information")
public class Page implements Serializable, Comparable<Page> {
    @Id
    @ApiModelProperty(value = "The url to access", required = true, example = "naciones/humanas")
    @NotEmpty
    private String url;

    @ApiModelProperty(value = "The title of the page", required = true, example = "naciones humanas")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "If the page is private", required = false, example = "false")
    @NotEmpty
    private Boolean isPrivate=false;

    @ApiModelProperty(value = "Term that are used as links", required = false, dataType="List", example = "nacion,Nación,nation")
    @NotEmpty
    private List<String> terms;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public void setTerms(String term) {
        this.terms = Arrays.asList(term.split(","));
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
    @Override
    public int hashCode() {
        final HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getUrl());
        hcb.append(getName());
        hcb.append(getIsPrivate());
        hcb.append(getTerms());
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Page)) {
            return false;
        }
        final Page other = ( Page ) object;
        final EqualsBuilder eqb = new EqualsBuilder();
        eqb.append(this.getUrl(), other.getUrl());
        eqb.append(this.getName(), other.getName());
        eqb.append(this.getIsPrivate(), other.getIsPrivate());
        eqb.append(this.getTerms(), other.getTerms());
        return eqb.isEquals();
    }

    @Override
    public int compareTo(Page other) {
        final CompareToBuilder ctb = new CompareToBuilder();
        ctb.append(this.getUrl(), other.getUrl());
        ctb.append(this.getName(), other.getName());
        ctb.append(this.getIsPrivate(), other.getIsPrivate());
        ctb.append(this.getTerms(), other.getTerms());
        return ctb.toComparison();
    }

}
