# AGENTS.md

A Kotlin/Spring Boot sample app that demonstrates a command-bus (CQRS-lite)
pattern: REST controllers dispatch `Request` objects through a `Bus` to
`Handler`s, resolved from Spring's `ApplicationContext` via a
reflection-based `Registry` (`src/main/kotlin/com/example/kotlincommandbus/config/`).

## Endpoints

- `POST /api/user`: save a user (command `SaveUserCommand`, `application/saveuser`)
- `GET /api/user/{id}`: get a user by id, 404 if unknown, 400 if not a UUID
  (query `GetUserByIdQuery`, `application/getuser`)
- `GET /api/user`: list all users (query `ListUsersQuery`, `application/listusers`)
- `GET /api/health`: health check, always `{"status":"UP"}` while the app runs (query `HealthCheckQuery`, `application/healthcheck`)

Writes are commands in `application/saveuser`; reads are queries in
`application/getuser` and `application/listusers`. `UserController` dispatches
all of them through `LoggingBus` and never calls a handler or `UserPort`
directly. `HealthController` likewise sends `HealthCheckQuery` only through
`LoggingBus` and never calls its handler directly. A new use case is a `Request` plus a `@Component` `Handler` in its own
`application/<usecase>` package; `Registry` picks it up automatically.

## Setup

No separate dependency install step: `./gradlew` resolves everything from
Maven Central on first run. No env files to copy.

```bash
./gradlew build
```

## Running

```bash
./gradlew bootRun --args='--server.port=<port>'
```

Defaults to port 8080 if `--server.port` isn't passed.

## Validation

```bash
./gradlew --no-daemon --console=plain build
```

Runs compilation (Kotlin's compiler is the typecheck here), the test suite,
and assembles the jar. There is no separate lint/format tool configured.

## Software factory

`/factory run` builds every open GitHub issue labeled `factory` through
intake → spec → build → gate → review → approve, one PR per issue. State is in
`factory/jobs/` (gitignored); `factory/bin/job` is its only writer. Checks:
`factory/checks.sh`. Dashboard: `cd factory && python3 -m http.server`, then
open http://localhost:8000/dashboard.html.

Each job works in its own git worktree, a full checkout of the repo at
`.worktrees/<job-id>` (gitignored). A tool run from the repo root that doesn't
read `.gitignore` sees those copies too; exclude `.worktrees/` in its config.
