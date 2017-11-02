package be.cegeka.ivolunteer.domain.common;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;

@MappedSuperclass
public abstract class Persistable {

    @Id
    protected String id;

    @Version
    @Column(nullable = false)
    private int versie;

    public int getVersie() {
        return versie;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || this.getClass() != other.getClass() || this.id == null)
            return false;
        return this.id.equals(((Persistable) other).id);    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + id;
    }
}
