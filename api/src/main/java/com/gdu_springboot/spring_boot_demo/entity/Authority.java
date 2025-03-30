    package com.gdu_springboot.spring_boot_demo.entity;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;

    @Entity
    @Table(name = "authorities")
    public class Authority {

        @Id
        @Column(name = "username", nullable = false)
        private String username;

        @Id
        @Column(name = "authority", nullable = false)
        private String authority;

        public Authority() {
        }

        public Authority(String username, String authority) {
            this.username = username;
            this.authority = authority;
        }

        // Getters và Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }
