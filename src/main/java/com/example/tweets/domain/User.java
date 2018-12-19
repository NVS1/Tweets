package com.example.tweets.domain;

import com.example.tweets.domain.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Login can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Name can't be empty")
    private String name;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @NotBlank(message = "Please enter your email")
    @Email(message = "Email is not correct")
    private String email;
    private String activationCode;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Image avatar;
    @ManyToMany
    @JoinTable(name = "subscriber_subscriptions",
            joinColumns = @JoinColumn(name = "subscriptions"),
            inverseJoinColumns = @JoinColumn(name = "subscribers"))
    private Set<User> subscribers = new HashSet<>();
    @ManyToMany(mappedBy = "likes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> likedMessages = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "subscriber_subscriptions",
            joinColumns = @JoinColumn(name = "subscribers"),
            inverseJoinColumns = @JoinColumn(name = "subscriptions"))
    private Set<User> subscriptions = new HashSet<>();

    public User() {
    }

    public Set<Message> getLikedMessages() {
        return likedMessages;
    }

    public void setLikedMessages(Set<Message> likedMessages) {
        this.likedMessages = likedMessages;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
    public Long getAvatarId (){
        return avatar.getId();
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean isAdmin (){
        return roles.contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public List<Message> getMessages() {
        messages.sort(Comparator.comparing(Message::getDate).reversed());
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public UserDTO toDTO (User currentUser){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setCurrentUser(this.equals(currentUser));
        userDTO.setName(name);
        userDTO.setPassword(password);
        userDTO.setSubscriber(this.subscribers.contains(currentUser));
        userDTO.setUsername(username);
        userDTO.setSubscribersCount(subscribers.size());
        userDTO.setSubscriptionsCount(subscriptions.size());
        userDTO.setAvatarId(avatar!=null?avatar.getId():null);
        return userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
