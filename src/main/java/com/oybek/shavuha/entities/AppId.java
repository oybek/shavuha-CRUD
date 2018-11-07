package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class AppId implements Serializable {

    @Column(name = "app")
    private String app;

    @Column(name = "id")
    private String id;

    public AppId(String app, String id) {
        this.app = app;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppId appId = (AppId) o;
        return Objects.equals(app, appId.app) &&
                Objects.equals(id, appId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(app, id);
    }
}

