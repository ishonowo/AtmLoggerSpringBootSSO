package com.infinity.app.model;

import java.util.Objects;

import jakarta.persistence.*;

/**
 * A single permission a user can hold, e.g. LOG_ISSUE or DISPLAY. This is the
 * "configurable in the database" part of the role system: adding a new role
 * is an INSERT into app_role, not a code change or a redeploy.
 */
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name; // matches a constant in RoleNames

    @Column(length = 200)
    private String description;

    public AppRole() {}

    public AppRole(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppRole other = (AppRole) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}


}
