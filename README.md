🛒 Microservice Tabanlı E-Ticaret Sistemi

Bu proje, Spring Boot tabanlı bir e-ticaret uygulamasıdır.
Kullanıcıların kayıt/giriş yapabildiği, ürün ekleyip sipariş verebildiği, ödeme işlemlerinin ayrı bir mikroservis tarafından simüle edildiği bir sistem sunar.
Redis, RabbitMQ ve JWT tabanlı authentication gibi modern teknolojiler kullanılmıştır.

🎯 Projenin Amacı

Microservice mantığını uygulamak: servisleri bağımsız sorumluluklarla bölmek

Gerçekçi senaryo uygulaması: ürün, sepet, sipariş ve ödeme süreçlerini uçtan uca kurmak

Modern entegrasyonlar: Redis (cache & sepet), RabbitMQ , Feign Client (servisler arası iletişim)

Güvenli kimlik doğrulama: JWT + Refresh Token mimarisi

⚙️ Kullanılan Teknolojiler
Teknoloji	Kullanım amacı
Spring Boot	Mikroservis geliştirme altyapısı
Spring Security + JWT	Kimlik doğrulama ve yetkilendirme
Redis	Sepet verilerinin hızlı tutulması & token management
RabbitMQ	Sipariş olaylarının publish edilmesi (event-driven yapı)
Spring Data JPA (Hibernate)	Veritabanı işlemleri
PostgreSQL	Ana veritabanı
OpenFeign	Mikroservisler arası senkron iletişim
Docker	Redis & RabbitMQ çalıştırmak için
Lombok	Kodun daha sade olması için (DTO/Entity)
🧩 Servisler ve Sorumluluklar

👤 Kullanıcı Servisi

Kullanıcı kayıt ve giriş

Şifreler BCrypt ile encode edilir

JWT + Refresh Token mimarisi

Redis ile token blacklisting

📦 Ürün Servisi

Ürün CRUD işlemleri

Ürünler ProductEntity ile temsil edilir

DTO (ProductRequestDto, ProductResponseDto) → katmanlar arası bağımsızlık

🛒 Sepet (Cart) Servisi

Kullanıcıya özel sepet Redis’te tutulur → hızlı erişim

Sepete ürün ekleme, listeleme, temizleme

Sipariş oluştururken Redis’ten ürünler alınır

📑 Sipariş Servisi

Redis’teki sepeti okuyarak sipariş oluşturur

OrderEntity ve OrderItemEntity ile sipariş + satır detaylarını kaydeder

Feign Client ile ödeme servisine bağlanır


💳 Ödeme Servisi (Ayrı Mikroservis)

Ayrı bir Spring Boot uygulaması

REST endpoint: /api/payments

e-ticaret-odeme-mikroservisi adlı repository'de mikroservise erişilebilir.

Ödeme işlemi kullanıcıdan alınan kart numarasına göre simüle edilmiştir

Senkron çalışır (OrderService ödeme yanıtını bekler)
