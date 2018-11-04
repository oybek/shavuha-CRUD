package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class AppId implements Serializable {
    @NonNull
    private String app;
    @NonNull
    private String id;

    public AppId(String app, String id) {
        this.app = app;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!AppId.class.isAssignableFrom(obj.getClass())) return false;
        final AppId other = (AppId) obj;
        return  this.app != null && this.app.equals(other.app) &&
                this.id != null && this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 37*app.hashCode()+id.hashCode();
    }
}

