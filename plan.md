Upgrade plan: Java 21

Context
- Automated `generate_upgrade_plan_for_java_project` tool was unavailable (requires Copilot Pro/Business). This plan is a manual substitute outlining safe steps to upgrade this Gradle Spring Boot project to Java 21.

Steps
1. Ensure JDK 21 is installed on the build machine (Windows):
   - Download and install a JDK 21 build (Adoptium/Eclipse Temurin or other vendor).
   - Set JAVA_HOME to the JDK 21 install path and add `%JAVA_HOME%\bin` to PATH.
   - Verify with `java -version` (should show 21).

2. Update Gradle Java toolchain (done):
   - `build.gradle` updated to use `JavaLanguageVersion.of(21)`.

3. Build and run tests locally:
   - Use the Gradle wrapper: `gradlew.bat clean build`.
   - Fix compile/test issues; common fixes: update third-party libs if they rely on older bytecode or removed APIs.

4. Update CI and dev environments:
   - Ensure CI runners use JDK 21 or install JDK 21 before building.
   - Update README and developer setup docs to reference Java 21.

5. Dependency review:
   - Verify Spring Boot and other dependencies are compatible with Java 21.
   - For any incompatible dependency, upgrade to a compatible version or apply code fixes.

6. Optional automated code migrations:
   - Use OpenRewrite recipes or other tools for large codebase changes.

7. Final verification:
   - Run the application (`gradlew bootRun`) and smoke-test critical endpoints.

Notes
- This repository already used Gradle toolchain (previously set to 17) — changing the toolchain is the minimal, recommended change.
- If you want, I can attempt to install JDK 21 on this environment and run the build here. Otherwise, follow the steps locally/CI.

Next actions I can take for you now
- Install or list JDKs available in this environment and attempt to run `gradlew.bat build`.
- Open a PR with the `build.gradle` change and `plan.md`.
- Run the build and share logs so we can fix any compile/test failures.
