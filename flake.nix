{
  description = "flake java gnu git";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = { self, nixpkgs }: {
    packages.x86_64-linux.hello = nixpkgs.legacyPackages.x86_64-linux.hello;
    packages.x86_64-linux.default = self.packages.x86_64-linux.default;

    devShells.default = nixpkgs.lib.mkShell {
      packages = with nixpkgs.legacyPackages.x86_64-linux; [
        pkgs.gnumake
        pkgs.git
        pkgs.zulu21
      ];
    };
  };
}
