# ☕ Simulador de Máquina de Café

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![POO](https://img.shields.io/badge/POO-Oriented-blue?style=for-the-badge)](https://github.com/jarib08/SimuladorCafeMachine)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

> Sistema completo de simulación de máquina de café desarrollado en Java con principios de Programación Orientada a Objetos (POO)

## 📋 Descripción

Este proyecto simula el funcionamiento completo de una máquina de café profesional, implementando una arquitectura robusta basada en POO. El sistema permite gestionar inventario, preparar bebidas, administrar usuarios, generar reportes y mantener persistencia de datos.

## ✨ Características

- **Gestión de Usuarios**: Sistema de autenticación y roles
- **Catálogo de Bebidas**: Múltiples tipos de café y bebidas personalizables
- **Control de Inventario**: Gestión automática de ingredientes y stock
- **Sistema de Reportes**: Generación de estadísticas y análisis de ventas
- **Simulación Interactiva**: Interfaz de usuario intuitiva
- **Persistencia de Datos**: Almacenamiento y recuperación de información

## 🏗️ Arquitectura del Proyecto

```
SimuladorCafeMachine/
├── src/
│   ├── models/          # Modelos de datos (Bebida, Usuario, Ingrediente)
│   ├── controllers/     # Lógica de negocio
│   ├── views/           # Interfaz de usuario
│   ├── services/        # Servicios (Inventario, Reportes)
│   └── utils/           # Utilidades y persistencia
├── datos/               # Archivos de datos persistentes
├── UML.jpg              # Diagrama UML del sistema
└── README.md
```

## 🎯 Principios de POO Aplicados

- **Encapsulamiento**: Protección de datos mediante modificadores de acceso
- **Herencia**: Jerarquía de clases para bebidas y usuarios
- **Polimorfismo**: Comportamientos específicos según tipo de bebida
- **Abstracción**: Interfaces y clases abstractas para componentes genéricos

## 🚀 Instalación y Uso

### Requisitos Previos

- Java JDK 8 o superior
- IDE compatible con Java (Eclipse, IntelliJ IDEA, NetBeans)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/jarib08/SimuladorCafeMachine.git
cd SimuladorCafeMachine
```

2. **Compilar el proyecto**
```bash
javac -d bin src/**/*.java
```

3. **Ejecutar la aplicación**
```bash
java -cp bin Main
```

## 📖 Casos de Uso

El proyecto incluye documentación detallada de casos de uso en el archivo `Proyecto Final - Casos de uso detallados.docx`, que describe:

- Preparación de bebidas
- Gestión de inventario
- Administración de usuarios
- Generación de reportes
- Mantenimiento del sistema

## 🗂️ Módulos Principales

### 1. Gestión de Bebidas
Permite crear, modificar y preparar diferentes tipos de bebidas con recetas personalizadas.

### 2. Control de Inventario
Monitorea niveles de ingredientes, alertas de stock bajo y reabastecimiento automático.

### 3. Sistema de Usuarios
Maneja autenticación, permisos y roles (administrador, operador, cliente).

### 4. Generación de Reportes
Produce estadísticas de ventas, consumo de ingredientes y análisis de tendencias.

### 5. Persistencia de Datos
Guarda y recupera información del sistema en archivos locales.

## 📊 Diagrama UML

El proyecto incluye un diagrama UML completo (`UML.jpg`) que muestra:
- Relaciones entre clases
- Atributos y métodos
- Patrones de diseño implementados

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Añadir nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

## 👥 Autores

- [@jarib08](https://github.com/jarib08) - Desarrollador
- [@Cris3x3] (https://github.com/Cris3x3) - Desarrollador
- [@JXNA007] (https://github.com/JXNA007) - Desarrollador
- [@Saravivas229-lang!] (https://github.com/Saravivas229-lang) - Desarrollador

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

## 📞 Contacto

Para preguntas o sugerencias, por favor abre un [issue](https://github.com/jarib08/SimuladorCafeMachine/issues) en el repositorio.

---

⭐ Si este proyecto te fue útil, considera darle una estrella en GitHub
