package com.infinity.app.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The application's own record for someone who logs in through ApacheDS.
 * ApacheDS proves who they are; this row (and the roles attached to it)
 * decides what they're allowed to do here. There is no password column -
 * credentials are never stored or checked by this application.
 */
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The value the person binds to ApacheDS with. Despite the column name,
    // this does NOT have to be an attribute literally called "uid" - in
    // this directory it holds the person's cn (e.g. "lola"), since that's
    // the attribute the user entries actually have. This is the join key
    // between the verified LDAP identity and this row.
    @Column(name = "ldap_cn", nullable = false, unique = true, length = 100)
    private String ldapCn;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    // Lets an admin switch off someone's app access here, without needing
    // rights to disable their account in the LDAP directory itself.
    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "last_login_at")
    private Instant lastLoginAt;

    // A user can hold several roles at once (e.g. LOG_ISSUE + LOGGED_CALLS).
    // EAGER because the full role set is needed on every login to build the
    // JWT, and the set per user is small.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AppRole> roles = new HashSet<>();

    public AppUser() {}

    public Set<String> getRoleNames() {
        return roles.stream().map(AppRole::getName).collect(Collectors.toSet());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLdapCn() { return ldapCn; }
    public void setLdapCn(String ldapCn) { this.ldapCn = ldapCn; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(Instant lastLoginAt) { this.lastLoginAt = lastLoginAt; }
    public Set<AppRole> getRoles() { return roles; }
    public void setRoles(Set<AppRole> roles) { this.roles = roles; }

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", ldapCn=" + ldapCn + ", email=" + email + ", fullName=" + fullName + ", active="
				+ active + ", createdAt=" + createdAt + ", lastLoginAt=" + lastLoginAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, createdAt, email, fullName, id, lastLoginAt, ldapCn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return active == other.active && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(lastLoginAt, other.lastLoginAt)
				&& Objects.equals(ldapCn, other.ldapCn);
	}
    
    
}
